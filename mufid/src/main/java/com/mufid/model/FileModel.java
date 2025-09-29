package com.mufid.model;


import java.time.LocalDateTime;

public class FileModel {
    private String fileName;
    private String originalFileName;
    private String fileExtension;
    private String mimeType;
    private Long fileSize;
    private String filePath;
    private String fileUrl;
    private String category; // PROFILE_PHOTO, DOCUMENT, REPORT, etc.
    private String description;
    private Boolean isPublic;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
    private String checksum;
    private String storageType; // LOCAL, S3, GCS, etc.
    private String bucketName;
    private String tags;

    public FileModel() {
        this.uploadedAt = LocalDateTime.now();
        this.isPublic = false;
    }

    public FileModel(String fileName, String originalFileName, String mimeType, Long fileSize) {
        this();
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.fileExtension = extractExtension(originalFileName);
    }

    // Utility methods
    private String extractExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf('.') > 0) {
            return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        }
        return "";
    }

    public boolean isImage() {
        return mimeType != null && mimeType.startsWith("image/");
    }

    public boolean isDocument() {
        return fileExtension != null && (
                fileExtension.equals("pdf") ||
                        fileExtension.equals("doc") ||
                        fileExtension.equals("docx") ||
                        fileExtension.equals("xls") ||
                        fileExtension.equals("xlsx")
        );
    }

    public String getFormattedFileSize() {
        if (fileSize == null) return "0 B";

        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double size = fileSize.doubleValue();

        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format("%.1f %s", size, units[unitIndex]);
    }

    // Getters and Setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }

    public String getFileExtension() { return fileExtension; }
    public void setFileExtension(String fileExtension) { this.fileExtension = fileExtension; }

    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsPublic() { return isPublic; }
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }

    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public String getChecksum() { return checksum; }
    public void setChecksum(String checksum) { this.checksum = checksum; }

    public String getStorageType() { return storageType; }
    public void setStorageType(String storageType) { this.storageType = storageType; }

    public String getBucketName() { return bucketName; }
    public void setBucketName(String bucketName) { this.bucketName = bucketName; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}
