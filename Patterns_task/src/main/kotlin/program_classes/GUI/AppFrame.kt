package program_classes.GUI

import javax.swing.*

class AppFrame: JFrame() {
    private lateinit var label: JLabel

    init{
        label = JLabel("Hello world")
        add(label)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        setSize(300,300)
        isVisible = true
    }
}