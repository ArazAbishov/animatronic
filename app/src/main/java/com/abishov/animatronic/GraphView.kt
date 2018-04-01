package com.abishov.animatronic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View

private const val MEASURED_STATE_SMALL = 0
private const val RADIUS = 128.0f

class GraphView : View {
  private val paint: Paint = Paint()
  private lateinit var boundingRectangle: RectF

  constructor(context: Context) : this(context, null)
  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
  constructor(context: Context, attrs: AttributeSet?, attr: Int) : super(context, attrs, attr) {
    paint.color = ContextCompat.getColor(context, R.color.colorAccent)
    paint.isAntiAlias = true
  }

  override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
    super.onSizeChanged(width, height, oldWidth, oldHeight)

    val horizontalPadding = paddingLeft + paddingRight
    val verticalPadding = paddingTop + paddingBottom

    val boundingWidth = width - horizontalPadding
    val boundingHeight = height - verticalPadding

    val diameter = Math.min(boundingWidth, boundingHeight).toFloat()
    boundingRectangle = RectF(0.0f, 0.0f, diameter, diameter)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val minimumWidth = paddingLeft + paddingRight + suggestedMinimumWidth
    val minimumHeight = paddingBottom + paddingTop + suggestedMinimumHeight

    val width = View.resolveSizeAndState(minimumWidth, widthMeasureSpec, MEASURED_STATE_SMALL)
    val height = View.resolveSizeAndState(minimumHeight, heightMeasureSpec, MEASURED_STATE_SMALL)

    setMeasuredDimension(width, height)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    canvas.drawCircle(
      boundingRectangle.centerX(),
      boundingRectangle.centerY(),
      RADIUS,
      paint
    )
  }
}