package org.alex;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import java.util.Collections;

public class ServiceTest {
    public static void main(String[] args) {

        // If you are having problems connecting the client node to the cluster,
        // you probably need to check these ports in the firewall settings:
        // 10800/tcp 47500/udp 47500/tcp 11211/tcp 47100/tcp
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("192.168.111.3:47500"));

        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
        //cfg.setPeerClassLoadingEnabled(true);

        Ignite ignite = Ignition.start(cfg);

        // If you want to start service at node startup
        // you need to add this in server node configuration and put class on nodes
        //    <property name="serviceConfiguration">
        //        <list>
        //            <bean class="org.apache.ignite.services.ServiceConfiguration">
        //                <property name="name" value="MyService"/>
        //                <property name="maxPerNodeCount" value="1"/>
        //                <property name="totalCount" value="1"/>
        //                <property name="service">
        //                    <bean class="org.alex.MyService"/>
        //                </property>
        //            </bean>
        //        </list>
        //    </property>

        IgniteServices service = ignite.services();
        service.deployClusterSingleton("MyService", new MyService());
        ignite.close();
    }
}