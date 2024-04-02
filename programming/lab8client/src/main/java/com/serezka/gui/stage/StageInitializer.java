package com.serezka.gui.stage;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private final StageHandler stageHandler;

    public StageInitializer(StageHandler stageHandler) {
        this.stageHandler = stageHandler;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        stageHandler.setPrimaryStage(event.getStage());
        stageHandler.showLoginScene();
    }
}

