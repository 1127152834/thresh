package com.zhang.thresh.common.core.exception;

/**
 * Thresh系统异常
 *
 * @author MrZhang
 */
public class ThreshException extends RuntimeException {

    private static final long serialVersionUID = -6916154462432027437L;

    public ThreshException(String message) {
        super(message);
    }
}
