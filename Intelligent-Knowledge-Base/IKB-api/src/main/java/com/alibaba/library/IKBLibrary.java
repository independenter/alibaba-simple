package com.alibaba.library;

public interface IKBLibrary {
    void inLibrary(IKBInvocation invocation);
    IKBInvocation outLibrary();
}
