package com.banap;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN aCommand);
}
