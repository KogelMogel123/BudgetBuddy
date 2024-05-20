using BudgetBuddyServer.Models;
using Microsoft.Extensions.Options;
using System.Net.Http.Headers;

namespace BudgetBuddyServer.Services
{
    public class MakeCommunicationService(IOptions<AppSettings> appSettings) : IMakeCommunicationService
    {
        private readonly AppSettings _appSettings = appSettings.Value;

        public async Task<HttpResponseMessage> SendFileToMake(IFormFile file)
        {
            using var client = new HttpClient();
            using var content = new MultipartFormDataContent("----generatedboundary");

            using (var memoryStream = new MemoryStream())
            {
                await file.CopyToAsync(memoryStream);
                var fileContent = new ByteArrayContent(memoryStream.ToArray());
                fileContent.Headers.ContentType = new MediaTypeHeaderValue("application/octet-stream");
                content.Add(fileContent, "file", file.FileName);
            }

            content.Add(new StringContent(file.FileName), "name");
            content.Add(new StringContent("application/octet-stream"), "mime");

            return await client.PostAsync(_appSettings.MakeConfiguration.Url, content);
        }
    }
}