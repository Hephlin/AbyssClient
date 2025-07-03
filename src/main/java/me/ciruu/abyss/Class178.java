package me.ciruu.abyss;

import java.util.concurrent.ConcurrentHashMap;
import me.ciruu.abyss.modules.Module;

public class Class178 {
    public void Method489(Module module) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.put("enabled", module.getStatus() ? "true" : "false");
        concurrentHashMap.put("display", module.moduleName());
        concurrentHashMap.put("keybind", module.getContext());
        concurrentHashMap.put("hidden", module.Method492() ? "true" : "false");
    }
}
