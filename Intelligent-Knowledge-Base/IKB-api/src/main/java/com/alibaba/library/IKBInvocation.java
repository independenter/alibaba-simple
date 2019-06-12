package com.alibaba.library;

public abstract class IKBInvocation<O> implements Invocation {
    public static final long serialVersionUID = -3647309088427840738L;
    /**
     * sign : 标识
     * instance: 传输实例
     */
    private String sign;

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    private O instance;

    public O getInstance() {
        return instance;
    }

    public void setInstance(O instance) {
        preProcess(instance);
        this.instance = instance;
        postProcess(instance);
    }
    public void preProcess(O instance){};
    public void postProcess(O instance){};
}
