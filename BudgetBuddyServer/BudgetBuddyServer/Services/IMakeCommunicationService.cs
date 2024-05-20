namespace BudgetBuddyServer.Services
{
    public interface IMakeCommunicationService
    {
        Task<HttpResponseMessage> SendFileToMake(IFormFile file);
    }
}