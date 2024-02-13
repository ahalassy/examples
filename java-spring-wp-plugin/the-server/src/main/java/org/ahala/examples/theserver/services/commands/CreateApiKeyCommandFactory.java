package org.ahala.examples.theserver.services.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateApiKeyCommandFactory {

    private final ApplicationContext applicationContext;

    public CreateApiKeyCommand createCommand() {
        return applicationContext.getBean(CreateApiKeyCommand.class);
    }

}
