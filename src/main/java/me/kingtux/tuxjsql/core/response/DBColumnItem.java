package me.kingtux.tuxjsql.core.response;

import java.util.UUID;

public interface DBColumnItem {


    <T> T getAsEnum();

    String getAsString();

    UUID getAsUUID();

    int getAsInt();

    long getAsLong();

    double getAsDouble();

    Object getAsObject();


    String getName();
}
