package com.loficostudios.melodyapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
    public static String removeExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName;
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }

        return fileName;
    }

    public static String serializeObjectToString(Object object) {
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            BukkitObjectOutputStream outputStream = new BukkitObjectOutputStream(io);
            outputStream.writeObject(object);
            outputStream.flush();
            byte[] obj = io.toByteArray();

            return Base64.getEncoder().encodeToString(obj);
        } catch (IOException e) {
            Bukkit.getLogger().log(
                    Level.SEVERE,
                    e.getMessage());
            return null;
        }
    }

    public static Object deserializeStringToObject(String string, Class<?> clazz) {
        Logger lgr = Bukkit.getLogger();

        byte[] obj = Base64.getDecoder().decode(string);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(obj);

        try {
            BukkitObjectInputStream bukkitInputStream = new BukkitObjectInputStream(inputStream);
            Object deserializedObj = bukkitInputStream.readObject();
            if (clazz.isInstance(deserializedObj)) {
                return clazz.cast(deserializedObj);
            } else {
                throw new ClassNotFoundException("Deserialized object is not of the expected type");
            }
        } catch (IOException e) {
            lgr.log(Level.SEVERE, e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            lgr .log(Level.SEVERE, "Cannot decode base64 byte array to ItemStack");
            return null;
        }
    }
}
