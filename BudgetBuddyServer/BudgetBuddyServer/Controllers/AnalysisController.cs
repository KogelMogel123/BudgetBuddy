using BudgetBuddyServer.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;

namespace BudgetBuddyServer.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    [AllowAnonymous]
    public class AnalysisController : ControllerBase
    {
        private readonly ILogger _logger;
        private readonly AppSettings _appSettings;

        public AnalysisController(ILogger<AnalysisController> logger, IOptions<AppSettings> appSettings)
        {
            _logger = logger;
            _appSettings = appSettings.Value;
        }

        [HttpPost]
        public async Task<IActionResult> Post(IFormFile file)
        {
            try
            {
                if (file == null || file.Length == 0)
                    return BadRequest("Nie wybrano pliku.");

                return Ok("OK");
            }
            catch (Exception ex)
            {
                _logger.LogCritical(ex, "Internal Server Error - Failed to process the POST request in AnalysisController class.");
                return BadRequest(new { Message = $"Internal Server Error." });
            }
        }
    }
}