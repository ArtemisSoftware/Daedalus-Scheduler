package com.artemissoftware.daedalusscheduler.data.models

import androidx.compose.ui.graphics.Color
import com.artemissoftware.daedalusscheduler.ui.theme.HighPriorityColor
import com.artemissoftware.daedalusscheduler.ui.theme.LowPriorityColor
import com.artemissoftware.daedalusscheduler.ui.theme.MediumPriorityColor
import com.artemissoftware.daedalusscheduler.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}