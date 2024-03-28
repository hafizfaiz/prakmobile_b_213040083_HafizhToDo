package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.agenda.models.Todo

@Composable

fun TodoItem(item: Todo) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp) )
    ){
        Row(Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(0.8f)) {
                Text(text = "Kegiatan ")
                Text(text = "Waktu")
                Text(text = "Tanggal")
                Text(text = "Deskripsi")
            }
            Column(modifier = Modifier.weight(0.1f)) {
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = item.title)
                Text(text = item.time)
                Text(text = item.dueDate)
                Text(text = item.description)
            }
        }
    }
}
