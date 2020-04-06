import { combineReducers, createStore, applyMiddleware } from 'redux'
import { Reducer } from './reducer'
import { State } from './openjdk/types'
import thunk from 'redux-thunk'

export type AppState = {
  state: State
}

export const store = createStore(
  combineReducers<AppState>({
    state: Reducer
  }),
  applyMiddleware(thunk)
)

export const makeStore = () => {
  return createStore(
    combineReducers<AppState>({
      state: Reducer
    }),
    applyMiddleware(thunk)
  )
}
