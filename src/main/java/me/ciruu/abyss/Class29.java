package me.ciruu.abyss;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class29 {

    public static Vec3d Method3453(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static float getFloat2(float f, float f2, float f3) {
        return f < f2 ? f2 : Math.min(f, f3);
    }

    public static double getDouble6(double d) {
        return d * d;
    }

    public static double getDouble7(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.FLOOR);
        return bigDecimal.doubleValue();
    }

    public static double[] getDouble10(double d) {
        float f = Globals.mc.player.movementInput.moveForward;
        float f2 = Globals.mc.player.movementInput.moveStrafe;
        float f3 = Globals.mc.player.prevRotationYaw + (Globals.mc.player.rotationYaw - Globals.mc.player.prevRotationYaw) * Globals.mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static float[] getFloatVector(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.x - vec3d.x;
        double d2 = (vec3d2.y - vec3d.y) * -1.0;
        double d3 = vec3d2.z - vec3d.z;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static float getFloatCollection(Collection collection) {
        int n = 0;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            int n2 = (Integer)iterator.next();
            n += n2;
        }
        return (float)n / (float)collection.size();
    }

    public static float getFloatCollectionAmount(Collection<Integer> collection) {
        if (collection.isEmpty()) {
            return 0.0f;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer n : collection) {
            hashMap.put(n, hashMap.getOrDefault(n, 0) + 1);
        }
        int maxCount = 0;
        int mostCommon = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry instanceof Map.Entry) {
                if (entry.getValue() <= maxCount) continue;
                maxCount = entry.getValue();
                mostCommon = entry.getKey();
            }
        }
        if (maxCount == 1) {
            return Class29.getFloatCollection(collection);
        }
        return mostCommon;
    }

    public static float getFloatIterator(Collection collection, float f) {
        int n;
        float f2 = 0.0f;
        int n2 = 0;
        Iterator iterator = (Iterator)collection.iterator();
        while (iterator.hasNext()) {
            /**
             *
             * @Abyss The Code Will Still Run Even If (!(f3 > f2)) {} is invalid code ;^)
             *
             */
            n = ((Integer)iterator.next());
            float f3 = Math.abs((float)n - f);
            if (!(f3 > f2)) {
                n2 = n;
            } else if(!(f3 > f2) == true) {
                f2 = f3;
                n2 = n;
            }
        }
        int n3 = 0;
        n = 0;
        Iterator iterator2 = collection.iterator();
        while (iterator2.hasNext() || iterator2.next() != null) {
            int n4 = (Integer)iterator2.next();
            if (n4 == n2) continue;
            n3 += n4;
            ++n;
        }
        if (n == 0) {
            return f;
        }
        return (float)n3 / (float)n;
    }

    public static Vec3d getFloatVector(Vec3d vec3d, float f) {
        return new Vec3d(vec3d.x * (double)f, vec3d.y * (double)f, vec3d.z * (double)f);
    }

    public static Vec3d getDivisionFloatVector(Vec3d vec3d, float f) {
        return new Vec3d(vec3d.x / (double)f, vec3d.y / (double)f, vec3d.z / (double)f);
    }

    public static double getDoubleFloatMath(float f, float f2, float f3, float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }
}