package display.img

import Scheduler
import kotlinx.coroutines.*
import log
import things.Cells.Cell
import things.Cells.Cell.Companion.cellList
import things.Microorganisms
import java.awt.Graphics
import java.awt.Image
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

class Frame {
    init {
        val frame = JFrame("Human Cells")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(1000,1000)
        frame.isResizable = true
        val p = MyPanel()
        p.StartDrawing()
        frame.add(p)
        frame.height
        frame.isVisible = true

    }


}

class MyPanel : JPanel() {
    private val imagePointMap = HashMap<Microorganisms, Point>()
    private val image = ImageIO.read(File("A:\\HumanCells\\src\\main\\kotlin\\display\\img\\macrophage.png")).getScaledInstance(200,200,
        Image.SCALE_SMOOTH)
    private val victim = ImageIO.read(File("A:\\HumanCells\\src\\main\\kotlin\\display\\img\\victim.png")).getScaledInstance(100,100,
        Image.SCALE_SMOOTH)




    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(image, imageLocation.x, imageLocation.y, this)
        for(c in Cell.cellList.indices){
            if(c==0) continue
            val cell = cellList[c]
            g.drawImage(victim,(cell.state.x*100).toInt()-100,(cell.state.y*100).toInt()-100,this)
        }

    }
    private var imageLocation = Point(0, 0)
    @OptIn(DelicateCoroutinesApi::class)
    fun moveImage(start: Point, end: Point, duration: Int) {
        val frame = 100
        val dx = (end.x - start.x) / (duration.toDouble()* frame)
        val dy = (end.y - start.y) / (duration.toDouble()* frame)
        GlobalScope.launch {
            val startTime = System.currentTimeMillis()
            // Duration * 1000 -> 1000
            // running time
            while (System.currentTimeMillis() - startTime < duration * 1000) {
                imageLocation.translate(dx.toInt(), dy.toInt())
                SwingUtilities.invokeLater {
                    repaint()
                }
                delay(1000L / frame)
            }

        }

    }

    fun StartDrawing(){
        log("startDrawing")
        Scheduler.instance.createRepeatingTask(1000L,0){
            val cell = if(Cell.cellList.isEmpty()) return@createRepeatingTask else Cell.cellList[0]
            if(imageLocation.getX()+200 != cell.state.x || imageLocation.getY()+200 != cell.state.y) moveImage(imageLocation,Point((cell.state.x*100).toInt()-200, (cell.state.y*100).toInt()-200),1)
        }
    }

    init {
//        val g = image.graphics
//        g.fillRect(0, 0, 100, 100)
//        g.dispose()
    }
}