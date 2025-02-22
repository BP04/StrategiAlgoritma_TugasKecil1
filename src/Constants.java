import java.awt.Color;
public class Constants {
    private static Color[] colors = {
        new Color(255, 0, 0),     // Red
        new Color(0, 255, 0),     // Green
        new Color(0, 0, 255),     // Blue
        new Color(255, 255, 0),   // Yellow
        new Color(255, 165, 0),   // Orange
        new Color(128, 0, 128),   // Purple
        new Color(0, 255, 255),   // Cyan
        new Color(255, 192, 203), // Pink
        new Color(165, 42, 42),   // Brown
        new Color(75, 0, 130),    // Indigo
        new Color(255, 215, 0),   // Gold
        new Color(173, 216, 230), // Light Blue
        new Color(0, 128, 0),     // Dark Green
        new Color(128, 128, 128), // Gray
        new Color(192, 192, 192), // Silver
        new Color(0, 0, 128),     // Navy
        new Color(128, 0, 0),     // Maroon
        new Color(0, 128, 128),   // Teal
        new Color(139, 69, 19),   // Saddle Brown
        new Color(46, 139, 87),   // Sea Green
        new Color(255, 99, 71),   // Tomato
        new Color(186, 85, 211),  // Medium Orchid
        new Color(30, 144, 255),  // Dodger Blue
        new Color(154, 205, 50),  // Yellow Green
        new Color(218, 112, 214), // Orchid
        new Color(107, 142, 35)   // Olive Drab
    };

    private static String[] ansi_colors = {
        "\u001B[31m",       // Red
        "\u001B[32m",       // Green
        "\u001B[34m",       // Blue
        "\u001B[33m",       // Yellow
        "\u001B[38;5;208m", // Orange
        "\u001B[35m",       // Purple
        "\u001B[36m",       // Cyan
        "\u001B[38;5;218m", // Pink
        "\u001B[38;5;130m", // Brown
        "\u001B[38;5;54m",  // Indigo
        "\u001B[38;5;220m", // Gold
        "\u001B[38;5;153m", // Light Blue
        "\u001B[38;5;22m",  // Dark Green
        "\u001B[38;5;244m", // Gray
        "\u001B[38;5;7m",   // Silver
        "\u001B[38;5;18m",  // Navy
        "\u001B[38;5;88m",  // Maroon
        "\u001B[38;5;30m",  // Teal
        "\u001B[38;5;94m",  // Saddle Brown
        "\u001B[38;5;29m",  // Sea Green
        "\u001B[38;5;203m", // Tomato
        "\u001B[38;5;134m", // Medium Orchid
        "\u001B[38;5;33m",  // Dodger Blue
        "\u001B[38;5;112m", // Yellow Green
        "\u001B[38;5;170m", // Orchid
        "\u001B[38;5;64m"   // Olive Drab
    };

    private static String reset = "\u001B[0m"; // Reset color

    public static int get_color(char ch){
        return colors[ch - 'A'].getRGB();
    }

    public static String get_colored_text(char ch){
        return ansi_colors[ch - 'A'] + ch + reset;
    }
}
