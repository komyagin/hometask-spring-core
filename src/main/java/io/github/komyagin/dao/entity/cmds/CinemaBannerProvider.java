package io.github.komyagin.dao.entity.cmds;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CinemaBannerProvider implements BannerProvider {
    @Override
    public String getProviderName() {
        return "Welcome to Echo Banner";
    }

    @Override
    public String getBanner() {
        StringBuffer buf = new StringBuffer();
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *" + OsUtils.LINE_SEPARATOR);
        buf.append("*          Cinema Management          *" +OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *" + OsUtils.LINE_SEPARATOR);
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("Version:" + this.getVersion());
        return buf.toString();
    }

    @Override
    public String getVersion() {
        return "0.1";
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Cinema management system!";
    }
}
