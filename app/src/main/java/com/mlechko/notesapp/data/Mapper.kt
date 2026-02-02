package com.mlechko.notesapp.data

import com.mlechko.notesapp.domain.ContentItem
import com.mlechko.notesapp.domain.Note
import kotlinx.serialization.json.Json

fun Note.toDbModel(): NoteDbModel {
    val contentAsString = Json.encodeToString(content.toContentItemDbModels())
    return NoteDbModel(id = this.id, title = this.title, content = contentAsString, isPinned = this.isPinned, updatedAt = this.updatedAt)
}

fun List<ContentItem>.toContentItemDbModels(): List<ContentItemDbModel> {
    return map { contentItem ->
        when (contentItem) {
            is ContentItem.Text -> {
                ContentItemDbModel.Text(content = contentItem.content)
            }
            is ContentItem.Image -> {
                ContentItemDbModel.Image(url = contentItem.url)
            }
        }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map {contentItemDbModel ->
        when(contentItemDbModel) {
            is ContentItemDbModel.Text -> {
                ContentItem.Text(contentItemDbModel.content)
            }
            is ContentItemDbModel.Image -> {
                ContentItem.Image(contentItemDbModel.url)
            }
        }

    }
}
fun NoteDbModel.toEntity(): Note {
    val contentAsObject = Json.decodeFromString<List<ContentItemDbModel>>(content)
    return Note(id = this.id, title = this.title, content = contentAsObject.toContentItems(), isPinned = this.isPinned, updatedAt = this.updatedAt)
}

fun List<NoteDbModel>.toEntities(): List<Note> {
    return map { it.toEntity() }
}