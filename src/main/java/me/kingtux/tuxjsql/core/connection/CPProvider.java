package me.kingtux.tuxjsql.core.connection;

import me.kingtux.tuxjsql.basic.connection.BasicConnectionProvider;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CPProvider {
    private static ServiceLoader<ConnectionProvider> loader = ServiceLoader
            .load(ConnectionProvider.class);

    public static List<ConnectionProvider> providers(boolean refresh) {
        if (refresh) {
            loader.reload();
        }
        List<ConnectionProvider> providers = new ArrayList<>();
        for(ConnectionProvider cp : loader){
            providers.add(cp);
        }

        return providers;
    }

    public static ConnectionProvider getCP() {

        List<ConnectionProvider> providers = providers(true);
        if (providers.size() == 1) {
            return providers.get(0);
        }
        for (ConnectionProvider provider : providers) {
            if (!provider.getClass().getCanonicalName().startsWith("dev.tuxjsql.basic")) {
                return provider;
            }
        }

        TuxJSQL.getLogger().warn("Unable to find ConnectionProvider using basic");
        return new BasicConnectionProvider();
    }
}
