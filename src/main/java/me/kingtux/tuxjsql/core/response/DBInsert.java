package me.kingtux.tuxjsql.core.response;

public interface DBInsert extends DBResult{

    @Override
    default int numberOfRows() {
        //This returns one since you can only insert one item at a time
        return 1;
    }

    Object primaryKey();


}
