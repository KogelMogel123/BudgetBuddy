namespace BudgetBuddyServer.Validations
{
    public static class FileValidation
    {
        public static bool IsImage(IFormFile file)
        {
            string[] permittedExtensions = { ".jpg", ".jpeg", ".png", ".bmp" };
            var ext = Path.GetExtension(file.FileName).ToLowerInvariant();

            if (string.IsNullOrEmpty(ext) || !permittedExtensions.Contains(ext))
            {
                return false;
            }

            if (file.ContentType.ToLower().StartsWith("image/"))
            {
                return true;
            }

            try
            {
                byte[] fileBytes;
                using (var ms = new MemoryStream())
                {
                    file.CopyTo(ms);
                    fileBytes = ms.ToArray();
                }

                return fileBytes.Length > 0 && IsImageFile(fileBytes);
            }
            catch
            {
                return false;
            }
        }

        private static bool IsImageFile(byte[] fileBytes)
        {
            var imageHeaders = new List<byte[]>
            {
                new byte[] { 0xFF, 0xD8 },               // JPEG
                new byte[] { 0x89, 0x50, 0x4E, 0x47 },   // PNG
                new byte[] { 0x42, 0x4D },               // BMP
            };

            foreach (var header in imageHeaders)
            {
                if (fileBytes.Take(header.Length).SequenceEqual(header))
                {
                    return true;
                }
            }

            return false;
        }
    }
}