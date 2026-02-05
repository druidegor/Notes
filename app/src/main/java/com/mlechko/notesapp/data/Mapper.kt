package com.mlechko.notesapp.data

import com.mlechko.notesapp.domain.ContentItem
import com.mlechko.notesapp.domain.Note
import kotlinx.serialization.json.Json

fun Note.toDbModel(): NoteDbModel {
    return NoteDbModel(id = this.id, title = this.title,isPinned = this.isPinned, updatedAt = this.updatedAt)
}

fun List<ContentItem>.toContentItemDbModels(noteId: Int): List<ContentItemDbModel> {
    return mapIndexed { index, contentItem ->
        when (contentItem) {
            is ContentItem.Image -> {
                ContentItemDbModel(
                    noteId = noteId,
                    contentType = ContentType.IMAGE,
                    content = contentItem.url,
                    order = index
                )
            }
            is ContentItem.Text -> {
                ContentItemDbModel(
                    noteId = noteId,
                    contentType = ContentType.TEXT,
                    content = contentItem.content,
                    order = index
                )
            }
        }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map {contentItemDbModel ->
        when(contentItemDbModel.contentType) {
            ContentType.TEXT -> {
                ContentItem.Text(contentItemDbModel.content)
            }
            ContentType.IMAGE -> {
                ContentItem.Image(contentItemDbModel.content)
            }
        }

    }
}
fun NoteWithContentDbModel.toEntity(): Note {
    return Note(
        id = noteDbModel.id,
        title = noteDbModel.title,
        content = content.toContentItems(),
        isPinned = noteDbModel.isPinned,
        updatedAt = noteDbModel.updatedAt
    )
}

fun List<NoteWithContentDbModel>.toEntities(): List<Note> {
    return map { it.toEntity() }
}