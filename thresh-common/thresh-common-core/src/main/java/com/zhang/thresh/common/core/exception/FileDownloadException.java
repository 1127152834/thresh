package com.zhang.thresh.common.core.exception;

/**
 * 文件下载异常
 *
 * @author MrZhang
 */
public class FileDownloadException extends RuntimeException {
    private static final long serialVersionUID = -4353976687870027960L;

    public FileDownloadException(String message) {
        super(message);
    }
}
