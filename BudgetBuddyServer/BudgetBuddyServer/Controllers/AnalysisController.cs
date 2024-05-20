using BudgetBuddyServer.Services;
using BudgetBuddyServer.Validations;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace BudgetBuddyServer.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    [AllowAnonymous]
    public class AnalysisController(ILogger<AnalysisController> logger, IMakeCommunicationService makeCommunicationService) : ControllerBase
    {
        private readonly ILogger _logger = logger;
        private readonly IMakeCommunicationService _makeCommunicationService = makeCommunicationService;

        [HttpPost]
        public async Task<IActionResult> Post(IFormFile file)
        {
            try
            {
                if (file == null || file.Length == 0)
                    return BadRequest("Error: The file is empty or has not been delivered.");

                const long maxFileSize = 1 * 1024 * 1024; // 1 MB in bytes
                if (file.Length > maxFileSize)
                    return BadRequest("The uploaded file exceeds the maximum size of 1MB.");

                if (!FileValidation.IsImage(file))
                    return BadRequest("Error: File is not an image.");

                var response = await _makeCommunicationService.SendFileToMake(file);

                if (response == null)
                {
                    return BadRequest("Internal Server Error.");
                }

                string responseBody = await response.Content.ReadAsStringAsync();
                _logger.LogInformation(response?.StatusCode.ToString());
                _logger.LogInformation(responseBody);

                if (response.IsSuccessStatusCode)
                {
                    return Ok("OK");
                }
                else
                {
                    return BadRequest(responseBody);
                }
            }
            catch (Exception ex)
            {
                _logger.LogCritical(ex, "Internal Server Error - Failed to process the POST request in AnalysisController class.");
                return BadRequest(new { Message = $"Internal Server Error." });
            }
        }
    }
}