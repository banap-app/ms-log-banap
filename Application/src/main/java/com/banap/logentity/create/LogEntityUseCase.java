package com.banap.logentity.create;

import com.banap.Result;
import com.banap.UseCase;
import com.banap.validation.handlers.Notification;

public abstract class LogEntityUseCase extends UseCase<LogEntityCommand, Result<LogEntityOutput, Notification>> {

}
