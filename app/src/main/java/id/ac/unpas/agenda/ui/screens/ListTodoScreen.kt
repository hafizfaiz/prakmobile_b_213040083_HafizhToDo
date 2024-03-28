package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import id.ac.unpas.agenda.models.Todo
import id.ac.unpas.agenda.persistences.TodoDao

@Composable
fun ListTodoScreen(todoDao: TodoDao) {
    val list by todoDao.loadAll().observeAsState(listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        list.forEach { todo ->
            TodoItem(item = todo)
        }
    }
}
