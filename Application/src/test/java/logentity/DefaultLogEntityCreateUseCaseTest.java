package logentity;

import com.banap.log_entity.LogEntity;
import com.banap.log_entity.LogEntityGateway;
import com.banap.logentity.create.DefaultLogEntityCreateUseCase;
import com.banap.logentity.create.LogEntityCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DefaultLogEntityCreateUseCaseTest {



    @InjectMocks
    private DefaultLogEntityCreateUseCase defaultLogEntityCreateUseCase;


    @Mock
    private LogEntityGateway logEntityGateway;

    @Test
    public void createEntityWithValidParams() {
        final var description = "Log Description";
        final var titleLog = "Log Title";
        final var authorApplication = "API Enginner Banap";
        final var logEntityTypeStatus = 1;
        final var logEntityTypeStatusName = "notification";
        final var logEntityTypeAction = 10;
        final var logEntityTypeActionName = "createField";
        final var propertyId = 12;
        final var userId = UUID.randomUUID();

        final var logEntity = LogEntity.newEntity(description, titleLog, authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);

        final var aCommand = LogEntityCommand.from(description, titleLog, authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);

        Mockito.when(logEntityGateway.create(Mockito.any())).thenReturn(logEntity);

        final var output = defaultLogEntityCreateUseCase.execute(aCommand).getFirstValue();

        Assertions.assertTrue(output.success());
        Assertions.assertEquals(titleLog,output.logEntity().getTitleLog());
        Assertions.assertEquals(description, output.logEntity().getDescription());
        Assertions.assertEquals(authorApplication, output.logEntity().getAuthorApplication());
        Assertions.assertEquals(logEntityTypeAction, output.logEntity().getLogEntityTypeAction().getTypeId());
        Assertions.assertEquals(logEntityTypeActionName, output.logEntity().getLogEntityTypeAction().getTypeName());
        Assertions.assertEquals(logEntityTypeStatusName, output.logEntity().getLogEntityTypeStatus().getTypeName());
        Assertions.assertEquals(propertyId, output.logEntity().getPropertyId());
        Assertions.assertEquals(userId, output.logEntity().getUserId());
        Assertions.assertEquals(logEntity.getId().getValue(), output.logEntity().getId().getValue());
    }
}
