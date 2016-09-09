package com.muckibude.cda.lightningtalktimer.injection;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public class TestAppComponentRule implements TestRule {
    public MockAppModule getMockAppModule() {
        return mockAppModule;
    }

    private MockAppModule mockAppModule;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                TestApp app = (TestApp) getTargetContext().getApplicationContext();
                try {
                    mockAppModule = new MockAppModule();
                    app.setOverrideModule(mockAppModule);
                    base.evaluate();
                } finally {
                    app.setOverrideModule(null);
                    DaggerAwareApplication.clearAppComponent(app);
                }
            }
        };
    }
}
