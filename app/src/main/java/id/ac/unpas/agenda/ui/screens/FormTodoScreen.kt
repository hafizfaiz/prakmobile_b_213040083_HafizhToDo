package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch

@Composable
fun FormTodoScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<TodoViewModel>()
    val scope = rememberCoroutineScope()

    val title = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    val time = remember { mutableStateOf(TextFieldValue("")) }
    val time2 = remember { mutableStateOf(TextFieldValue("")) }
    val date = remember { mutableStateOf(TextFieldValue("")) }
    val month = remember { mutableStateOf(TextFieldValue("")) }
    val year = remember { mutableStateOf(TextFieldValue("")) }

    Text(
        text = "Havizs To Do",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier
        .fillMaxWidth()) {


        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Nama Kegiatan") },
                modifier = Modifier.fillMaxWidth(),
                value = title.value,
                onValueChange = {
                    title.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Deskripsi") },
                modifier = Modifier.fillMaxWidth(),
                value = description.value,
                onValueChange = {
                    description.value = it
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = "Jam") },
                    modifier = Modifier.weight(1f),
                    value = time.value,
                    onValueChange = { time.value = it }
                )
                Text(
                    text = ":",
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "Menit") },
                    modifier = Modifier.weight(1f),
                    value = time2.value,
                    onValueChange = { time2.value = it }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = "Tanggal") },
                    modifier = Modifier.weight(1f),
                    value = date.value,
                    onValueChange = { date.value = it }
                )
                Text(
                    text = " ",
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "Bulan") },
                    modifier = Modifier.weight(1f),
                    value = month.value,
                    onValueChange = { month.value = it }
                )
                Text(
                    text = " ",
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                OutlinedTextField(
                    label = { Text(text = "Tahun") },
                    modifier = Modifier.weight(1f),
                    value = year.value,
                    onValueChange = { year.value = it }
                )
            }
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(5f),
                    onClick = {
                        scope.launch {
                            val timeFormatted = "${time.value.text}:${time2.value.text}"
                            val dateFormatted = "${date.value.text} ${month.value.text} ${year.value.text}"

                            if (id != null) {
                                viewModel.update(
                                    id,
                                    title.value.text,
                                    description.value.text,
                                    timeFormatted,
                                    dateFormatted
                                )
                            } else {
                                viewModel.insert(
                                    uuid4().toString(),
                                    title.value.text,
                                    description.value.text,
                                    timeFormatted,
                                    dateFormatted
                                )
                            }
                        }
                    }
                )
                {
                    Text(text = "Simpan")
                }
                Button(
                    modifier = Modifier.weight(5f),
                    onClick = {
                        title.value = TextFieldValue("")
                        description.value = TextFieldValue("")
                        time.value = TextFieldValue("")
                        time2.value = TextFieldValue("")
                        date.value = TextFieldValue("")
                        month.value = TextFieldValue("")
                        year.value = TextFieldValue("")
                    }
                ) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                title.value = TextFieldValue("")
                description.value = TextFieldValue("")
                time.value = TextFieldValue("")
                time2.value = TextFieldValue("")
                date.value = TextFieldValue("")
            }
        }

        LaunchedEffect(id) {
            if (id != null) {
                scope.launch {
                    viewModel.find(id)
                }
            }
        }

        viewModel.item.observe(LocalLifecycleOwner.current) {
            title.value = TextFieldValue(it.title)
            description.value = TextFieldValue(it.description)
            time.value = TextFieldValue(it.time)
            date.value = TextFieldValue(it.dueDate)
        }
    }

}