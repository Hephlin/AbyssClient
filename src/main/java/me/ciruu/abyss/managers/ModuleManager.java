package me.ciruu.abyss.managers;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.*;

import me.ciruu.abyss.modules.Module;

public class ModuleManager {
    public static final ClassToInstanceMap<Module> module_val = MutableClassToInstanceMap.create();

    // Use wildcard-bound Collection for type safety
    public static final Collection<? extends Module> modulecollection = module_val.values();

    public void mods() {
        Iterator<Module> iterator = module_val.values().iterator();
        while (iterator.hasNext()) {
            Module module = iterator.next();
            if (module != null) {
                Module.setup();
            } else {
                System.err.println("ModuleManager: Found a null module while registering!");
            }
        }
    }

    public Module getModuleByClass(Class<? extends Module> clazz) {
        for (Module module : modulecollection) {
            if (module instanceof Module) {
                if (module.getClass().equals(clazz)) {
                    return module;
                }
            }
        }
        System.err.println("Module not found: " + clazz.getName());
        return null;
    }

    public Module getModuleByName(String name) {
        Collection<?> modules = module_val.values();
        for (Object module_ : modules) {
            if (module_ instanceof Module) {
                if (((Module) module_).moduleName().equalsIgnoreCase(name)) {
                    return (Module) module_;
                }
            }
        }

        System.err.println("[ModuleManager] WARNING: Module not found by name: " + name);
        return null;
    }

    public Map<Class<? extends Module>, Module> getModules() {
        return module_val;
    }


    public boolean isModuleEnabled(Class clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module == null) {
            return false;
        }
        if (!Module.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (module.isEnabled()) {
            return true;
        }
        return false;
    }

    private static boolean getString2(String string, Module module) {
        return module.getContext().equals(string);
    }

    public static void getString1(String string) {
        if (string == null || string.isEmpty() || string.equalsIgnoreCase("NONE")) {
            return;
        }
        module_val.values().stream().filter(arg_0 -> ModuleManager.getString2(string, (Module) arg_0)).forEach(null);
    }

    public void addModule(Module module) {
        module_val.put(module.getClass(), module);
    }
}
