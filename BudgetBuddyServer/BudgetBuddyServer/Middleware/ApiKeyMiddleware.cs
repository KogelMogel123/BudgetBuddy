using BudgetBuddyServer.Models;
using Microsoft.Extensions.Options;

namespace BudgetBuddyServer.Middleware
{
    public class ApiKeyMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly AppSettings _appSettings;

        public ApiKeyMiddleware(RequestDelegate next, IOptions<AppSettings> appSettings)
        {
            _next = next;
            _appSettings = appSettings.Value;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            if (!context.Request.Headers.TryGetValue(_appSettings.ApiUsageSettings.ApiKeyHeaderName, out var extractedApiKey))
            {
                context.Response.StatusCode = 401; // Unauthorized
                await context.Response.WriteAsync("Api Key was not provided.");
                return;
            }

            if (!IsValidApiKey(extractedApiKey))
            {
                context.Response.StatusCode = 401; // Unauthorized
                await context.Response.WriteAsync("Unauthorized client.");
                return;
            }

            await _next(context);
        }

        private bool IsValidApiKey(string apiKey)
        {
            return apiKey == _appSettings.ApiUsageSettings.ValidApiKey;
        }
    }
}