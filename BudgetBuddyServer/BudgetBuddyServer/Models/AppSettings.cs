namespace BudgetBuddyServer.Models
{
    public class AppSettings
    {
        public MakeConfiguration MakeConfiguration { get; set; }
    }

    public class MakeConfiguration
    {
        public string Url { get; set; }
    }
}