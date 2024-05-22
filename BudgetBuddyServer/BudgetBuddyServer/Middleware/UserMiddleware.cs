using BudgetBuddyServer.Models;
using Microsoft.Extensions.Caching.Memory;
using Microsoft.Extensions.Options;

namespace BudgetBuddyServer.Middleware
{
    public class UserMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly IMemoryCache _cache;
        private readonly AppSettings _appSettings;

        public UserMiddleware(RequestDelegate next, IMemoryCache cache, IOptions<AppSettings> appSettings)
        {
            _next = next;
            _cache = cache;
            _appSettings = appSettings.Value;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            if (!context.Request.Headers.TryGetValue(_appSettings.ApiUsageSettings.UserHeaderName, out var user))
            {
                context.Response.StatusCode = 401; // Unauthorized
                await context.Response.WriteAsync("User was not provided.");
                return;
            }

            if (string.IsNullOrEmpty(user))
            {
                context.Response.StatusCode = 401; // Unauthorized
                await context.Response.WriteAsync("User was not provided.");
                return;
            }

            if (IsUserBlocked(user))
            {
                context.Response.StatusCode = 429; // Too Many Requests
                await context.Response.WriteAsync("User is blocked. Try again later.");
                return;
            }

            IncrementApiKeyUsage(user);

            await _next(context);
        }

        private bool IsUserBlocked(string user)
        {
            var cacheEntry = _cache.Get<CacheEntry>(user);
            return cacheEntry != null && cacheEntry.IsBlocked;
        }

        private void IncrementApiKeyUsage(string user)
        {
            if (!_cache.TryGetValue(user, out CacheEntry cacheEntry))
            {
                cacheEntry = new CacheEntry { Count = 0, IsBlocked = false };
            }

            cacheEntry.Count++;

            if (cacheEntry.Count >= _appSettings.ApiUsageSettings.RequestLimit)
            {
                cacheEntry.IsBlocked = true;
                _cache.Set(user, cacheEntry, TimeSpan.FromMinutes(_appSettings.ApiUsageSettings.BlockDurationMinutes));
            }
            else
            {
                _cache.Set(user, cacheEntry);
            }
        }

        private class CacheEntry
        {
            public int Count { get; set; }
            public bool IsBlocked { get; set; }
        }
    }
}