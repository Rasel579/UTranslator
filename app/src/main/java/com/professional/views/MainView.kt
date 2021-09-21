package com.professional.views

import com.professional.models.AppState

interface MainView {
  fun renderData(appState: AppState)
}