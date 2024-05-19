using BudgetBuddyServer.Models;

namespace BudgetBuddyServer.Services
{
    public static class ServiceConfiguration
    {
        public static void AddCustomServices(this IServiceCollection services, IConfiguration configuration)
        {
            var appSettings = new AppSettings();

            configuration.GetSection("AppSettings").Bind(appSettings);

            services.Configure<AppSettings>(configuration.GetSection("AppSettings"));

            services.AddScoped<IMakeCommunicationService, MakeCommunicationService>();
        }
    }
}