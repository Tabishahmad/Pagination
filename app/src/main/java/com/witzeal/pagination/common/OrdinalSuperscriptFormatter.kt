//package com.witzeal.pagination.common
//
//import android.text.SpannableStringBuilder
//import android.text.Spanned
//import android.text.style.RelativeSizeSpan
//import android.text.style.SuperscriptSpan
//import android.widget.TextView
//import java.util.regex.Pattern
//
//class OrdinalSuperscriptFormatter(private val stringBuilder: SpannableStringBuilder) {
//    private val SUPERSCRIPT_REGEX = "(?<=\\b\\d{0,10})(st|nd|rd|th)(?=\\b)".toRegex()
//    private val PROPORTION = 0.5f
//
//    fun format(textView: TextView) {
//        val text = textView.text
//        val matcher = SUPERSCRIPT_REGEX.find(text)
//        stringBuilder.clear()
//        stringBuilder.append(text)
//        matcher?.let {
//            createSuperscriptSpan(it.range.start, it.range.endInclusive)
//        }
//        textView.text = stringBuilder
//    }
//
//    private fun createSuperscriptSpan(start: Int, end: Int) {
//        val superscript = SuperscriptSpan()
//        val size = RelativeSizeSpan(PROPORTION)
//        stringBuilder.setSpan(superscript, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        stringBuilder.setSpan(size, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//    }
//}
