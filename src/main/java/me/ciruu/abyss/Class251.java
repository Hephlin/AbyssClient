package me.ciruu.abyss;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import me.ciruu.abyss.Class250;
import me.ciruu.abyss.Globals;
import me.ciruu.abyss.managers.RunnableManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Class251 {
    private static final Logger Field809;
    static final boolean Field810;

    public static void Method1066(String string, String string2, Consumer consumer) {
        RunnableManager.runRunnable(() -> Class251.Method1067(string, string2, consumer));
    }

    private static void Method1068(DynamicTexture dynamicTexture) {
        try {
            for (Field field : DynamicTexture.class.getDeclaredFields()) {
                if (field instanceof Field) {
                    if (!field.getType().getTypeName().equals(int[].class.getTypeName())) continue;
                    field.setAccessible(true);
                    field.set(dynamicTexture, new int[0]);
                    break;
                }
            }
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    public static void Method1069(String string, String string2, BiConsumer biConsumer) {
        RunnableManager.runRunnable(() -> Class251.Method1070(string, string2, biConsumer));
    }

    private static void Method1071(InputStream inputStream, Consumer consumer) throws IOException {
        int n;
        IIOMetadataNode iIOMetadataNode;
        BufferedImage object3;
        ArrayList<Object> arrayList = new ArrayList<>(2);
        ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
        imageReader.setInput(ImageIO.createImageInputStream(inputStream), false);

        int n2 = 0;
        int n3 = 0;
        int n4 = -1;
        int n5 = -1;
        IIOMetadata iIOMetadata = imageReader.getStreamMetadata();
        Color color = null;

        if (iIOMetadata != null) {
            IIOMetadataNode metadataRoot = (IIOMetadataNode) iIOMetadata.getAsTree(iIOMetadata.getNativeMetadataFormatName());
            NodeList nodeList = metadataRoot.getElementsByTagName("GlobalColorTable");
            NodeList nodeList2 = metadataRoot.getElementsByTagName("LogicalScreenDescriptor");

            if (nodeList2 != null && nodeList2.getLength() > 0) {
                IIOMetadataNode node = (IIOMetadataNode) nodeList2.item(0);
                n4 = Integer.parseInt(node.getAttribute("logicalScreenWidth"));
                n5 = Integer.parseInt(node.getAttribute("logicalScreenHeight"));
            }

            if (nodeList != null && nodeList.getLength() > 0) {
                IIOMetadataNode globalColorTable = (IIOMetadataNode) nodeList.item(0);
                String bgIndex = globalColorTable.getAttribute("backgroundColorIndex");

                for (Node child = globalColorTable.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child instanceof Node) {
                        IIOMetadataNode entry = (IIOMetadataNode) child;
                        if (entry.getAttribute("index").equals(bgIndex)) {
                            int red = Integer.parseInt(entry.getAttribute("red"));
                            int green = Integer.parseInt(entry.getAttribute("green"));
                            int blue = Integer.parseInt(entry.getAttribute("blue"));
                            color = new Color(red, green, blue);
                            break;
                        }
                    }
                }
            }
        }

        object3 = null;
        boolean bl = false;
        int n8 = 0;

        while (true) {
            BufferedImage frame;
            try {
                frame = imageReader.read(n8);
            } catch (IndexOutOfBoundsException e) {
                break;
            }

            if (n4 == -1 || n5 == -1) {
                n4 = frame.getWidth();
                n5 = frame.getHeight();
            }

            IIOMetadataNode meta = (IIOMetadataNode) imageReader.getImageMetadata(n8).getAsTree("javax_imageio_gif_image_1.0");
            iIOMetadataNode = (IIOMetadataNode) meta.getElementsByTagName("GraphicControlExtension").item(0);
            NodeList nodeList = meta.getChildNodes();
            n = Integer.parseInt(iIOMetadataNode.getAttribute("delayTime"));
            String string = iIOMetadataNode.getAttribute("disposalMethod");

            if (object3 == null) {
                object3 = new BufferedImage(n4, n5, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = object3.createGraphics();
                g2d.setColor(color);
                g2d.fillRect(0, 0, object3.getWidth(), object3.getHeight());
                g2d.drawImage(frame, 0, 0, null);
                g2d.dispose();
                bl = frame.getWidth() == n4 && frame.getHeight() == n5;
            } else {
                int n10 = 0;
                int n11 = 0;

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (!node.getNodeName().equals("ImageDescriptor")) continue;

                    NamedNodeMap attrs = node.getAttributes();
                    n10 = Integer.parseInt(attrs.getNamedItem("imageLeftPosition").getNodeValue());
                    n11 = Integer.parseInt(attrs.getNamedItem("imageTopPosition").getNodeValue());
                }

                if (string.equals("restoreToPrevious")) {
                    BufferedImage prevImage = null;
                    for (int i = n8 - 1; i >= 0; --i) {
                        if (((Class250) arrayList.get(i)).Method956().equals("restoreToPrevious") && n8 != 0) continue;
                        prevImage = ((Class250) arrayList.get(i)).Method954();
                        break;
                    }
                    if (!Field810 && prevImage == null) throw new AssertionError();

                    ColorModel cm = prevImage.getColorModel();
                    boolean isAlpha = prevImage.isAlphaPremultiplied();
                    WritableRaster raster = prevImage.copyData(null);
                    object3 = new BufferedImage(cm, raster, isAlpha, null);
                } else if (string.equals("restoreToBackgroundColor") && color != null && (!bl || n8 > 1)) {
                    Graphics2D g2d = object3.createGraphics();
                    g2d.setColor(color);
                    g2d.fillRect(n2, n3, ((Class250) arrayList.get(n8 - 1)).Method957(), ((Class250) arrayList.get(n8 - 1)).Method958());
                    g2d.dispose();
                }

                Graphics2D g2d = object3.createGraphics();
                g2d.drawImage(frame, n10, n11, null);
                g2d.dispose();

                n2 = n10;
                n3 = n11;
            }

            ColorModel cm = object3.getColorModel();
            boolean isAlpha = object3.isAlphaPremultiplied();
            WritableRaster raster = object3.copyData(null);
            BufferedImage finalFrame = new BufferedImage(cm, raster, isAlpha, null);
            Class250 class250 = new Class250(finalFrame, n, string, frame.getWidth(), frame.getHeight());
            arrayList.add(class250);
            consumer.accept(class250);

            System.gc();
            frame.flush();
            n8++;
        }

        imageReader.dispose();
        System.gc();
    }


    private static void Method1070(String string, String string2, BiConsumer biConsumer) {
        try {
            Field809.info("Loading image '" + string + "' from" + string2);
            InputStream inputStream = new URL(string2).openStream();
            Class251.Method1071(inputStream, arg_0 -> Class251.Method1072(string, biConsumer, (Class250) arg_0));
            inputStream.close();
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static void Method1072(String string, BiConsumer biConsumer, Class250 class250) {
        RunnableManager.addRunnableAsTask(() -> Class251.Method1073(class250, string, biConsumer));
    }

    private static void Method1073(Class250 class250, String string, BiConsumer biConsumer) {
        DynamicTexture dynamicTexture = new DynamicTexture(Class250.Method959(class250));
        ResourceLocation resourceLocation = Globals.mc.getTextureManager().getDynamicTextureLocation("abyss/capes_" + string, dynamicTexture);
        Class251.Method1068(dynamicTexture);
        biConsumer.accept(resourceLocation, Class250.Method960(class250));
        int n = Class250.Method959(class250).getWidth() * Class250.Method959(class250).getHeight() * 4 / 1024;
        Field809.info("Loaded image '" + string + "' (" + Class250.Method959(class250).getWidth() + " x" + Class250.Method959(class250).getHeight() + ") bytes:" + n + "kB");
    }

    private static void Method1067(String string, String string2, Consumer consumer) {
        try {
            Field809.info("Loading image '" + string + "' from" + string2);
            BufferedImage bufferedImage = ImageIO.read(new URL(string2));
            RunnableManager.addRunnableAsTask(() -> Class251.Method1074(bufferedImage, string, consumer));
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static void Method1074(BufferedImage bufferedImage, String string, Consumer consumer) {
        DynamicTexture dynamicTexture = new DynamicTexture(bufferedImage);
        ResourceLocation resourceLocation = Globals.mc.getTextureManager().getDynamicTextureLocation("abyss/capes_" + string, dynamicTexture);
        Class251.Method1068(dynamicTexture);
        consumer.accept(resourceLocation);
        int n = bufferedImage.getWidth() * bufferedImage.getHeight() * 4 / 1024;
        Field809.info("Loaded image '" + string + "' (" + bufferedImage.getWidth() + " x" + bufferedImage.getHeight() + ") bytes:" + n + "kB");
    }

    static {
        Field810 = !Class251.class.desiredAssertionStatus();
        Field809 = LogManager.getLogger((String)"Image Util");
    }
}
