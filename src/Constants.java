public class Constants {
    private static final String[] colors = {
        "\u001B[31m",
        "\u001B[32m",
        "\u001B[33m",
        "\u001B[34m",
        "\u001B[35m",
        "\u001B[36m",
        "\u001B[91m",
        "\u001B[92m",
        "\u001B[93m",
        "\u001B[94m",
        "\u001B[95m",
        "\u001B[96m",
        "\u001B[37m",
        "\u001B[90m",
        "\u001B[38;5;208m",
        "\u001B[38;5;45m",
        "\u001B[38;5;81m",
        "\u001B[38;5;141m",
        "\u001B[38;5;129m",
        "\u001B[38;5;87m",
        "\u001B[38;5;220m",
        "\u001B[38;5;214m",
        "\u001B[38;5;51m",
        "\u001B[38;5;201m",
        "\u001B[38;5;105m",
        "\u001B[38;5;170m"
    };
    
    private static final String reset = "\u001B[0m"; // Reset color

    public static String get_color(char ch){
        return colors[ch - 'A'] + ch + reset;
    }
}
