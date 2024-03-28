package id.ac.unpas.agenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val dueDate: String
)

