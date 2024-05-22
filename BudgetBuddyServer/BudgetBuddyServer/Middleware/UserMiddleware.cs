using Microsoft.Extensions.Caching.Memory;

namespace BudgetBuddyServer.Middleware
{
    public class UserMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly IMemoryCache _cache;
        private const string USER_HEADER_NAME = "User";
        private const int REQUEST_LIMIT = 5;
        private const int BLOCK_DURATION_MINUTES = 60;

        public UserMiddleware(RequestDelegate next, IMemoryCache cache)
        {
            _next = next;
            _cache = cache;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            if (!context.Request.Headers.TryGetValue(USER_HEADER_NAME, out var user))
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

            if (cacheEntry.Count >= REQUEST_LIMIT)
            {
                cacheEntry.IsBlocked = true;
                _cache.Set(user, cacheEntry, TimeSpan.FromMinutes(BLOCK_DURATION_MINUTES));
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