namespace BudgetBuddyServer.Models
{
    public class AppSettings
    {
        public MakeConfiguration MakeConfiguration { get; set; }
        public ApiUsageSettings ApiUsageSettings { get; set; }
    }

    public class MakeConfiguration
    {
        public string Url { get; set; }
    }

    public class ApiUsageSettings
    {
        public string ApiKeyHeaderName { get; set; }
        public string UserHeaderName { get; set; }
        public short RequestLimit { get; set; }
        public short BlockDurationMinutes { get; set; }
        public string ValidApiKey { get; set; }
    }
}