package me.ciruu.abyss;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import me.ciruu.abyss.Manager;

public class Class106
implements Runnable {
    boolean Field1761;
    private volatile Thread Field1768;
    private boolean Field1769;
    private LinkedList Field1770;

    public Class106() {
    }

    public boolean isWord(String string) {
        return false;
    }

    @Override
    public void run() {
        Manager.logger.debug("Recognition thread starting");
        Manager.logger.debug("Recognition thread finished");
    }

    public int getQueueSize() {
        return this.Field1770.size();
    }

    public String popString() {
        if (this.getQueueSize() > 0) {
            return (String)this.Field1770.removeFirst();
        }
        return "";
    }

    public boolean isEnabled() {
        return isEnabled();
    }

    public Object setEnabled(boolean bl) {
        return null;
    }

    public void destroy() {
    }

    public LinkedList getRecognizedStringQueue() {
        return this.Field1770;
    }
}
