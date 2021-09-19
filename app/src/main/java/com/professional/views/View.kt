package com.professional.views

import com.professional.models.AppState

interface View {
  fun renderData(appState: AppState)
}