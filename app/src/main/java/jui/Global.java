package jui;

import jui.gfx.Window;

public class Global {
    private static final Window m_Window = new Window();
    public static Window getWindow() {
        return m_Window;
    }
}
