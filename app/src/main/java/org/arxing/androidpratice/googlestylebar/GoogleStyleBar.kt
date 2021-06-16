package org.arxing.androidpratice.googlestylebar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import io.github.florent37.shapeofview.ShapeOfView
import io.github.florent37.shapeofview.manager.ClipPathManager
import org.arxing.androidpratice.R

class GoogleStyleBar : ShapeOfView {

    private var centerX = 0f
    private var clipLeft = 0f
    private var clipTop = 0f
    private var clipRight = 0f
    private var clipBottom = 0f
    private var radius = 0f
    private var centerCirclePadding = 0f
    private var borderWidthPx = 10f
    private var borderColor = Color.RED
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val borderPath: Path = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.GoogleStyleBar
        ).apply {
            centerCirclePadding = getDimension(R.styleable.GoogleStyleBar_bar_circlePadding, 0f)
            borderWidthPx = getDimension(R.styleable.GoogleStyleBar_bar_borderWidth, 0f)
            borderColor = getColor(R.styleable.GoogleStyleBar_bar_borderColor, Color.TRANSPARENT)
        }.recycle()

        setClipPathCreator(object : ClipPathManager.ClipPathCreator {

            override fun createClipPath(width: Int, height: Int): Path {
                centerX = width / 2f
                clipLeft = 0f
                clipTop = 0f
                clipRight = width.toFloat()
                clipBottom = height.toFloat()
                radius = height / 2f + centerCirclePadding

                return Path().apply {
                    val circleLeft = centerX - radius
                    val circleRight = centerX + radius
                    val circleTop = -radius
                    val circleBottom = radius
                    moveTo(clipLeft, clipTop)
                    lineTo(circleLeft, clipTop)
                    arcTo(circleLeft, circleTop, circleRight, circleBottom, 180f, -180f, false)
                    lineTo(clipRight, clipTop)
                    lineTo(clipRight, clipBottom)
                    lineTo(clipLeft, clipBottom)
                    close()

                    borderPath.reset()
                    borderPath.moveTo(clipLeft, clipTop)
                    borderPath.lineTo(circleLeft, clipTop)
                    borderPath.arcTo(
                        circleLeft,
                        circleTop,
                        circleRight,
                        circleBottom,
                        180f,
                        -180f,
                        false
                    )
                    borderPath.lineTo(clipRight, clipTop)
                }
            }

            override fun requiresBitmap(): Boolean = false
        })
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        if (borderWidthPx > 0) {
            borderPaint.strokeWidth = borderWidthPx
            borderPaint.color = borderColor
            canvas?.drawPath(borderPath, borderPaint)
        }
    }
}