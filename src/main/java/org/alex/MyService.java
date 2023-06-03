package org.alex;

import org.apache.ignite.services.Service;

public class MyService implements Service {
    @Override
    public void init() throws Exception {
        System.out.println("MyService init");
        Service.super.init();
    }

    @Override
    public void execute() throws Exception {
        Service.super.execute();
    }

    @Override
    public void cancel() {
        Service.super.cancel();
    }
}
