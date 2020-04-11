import {
  LIST,
  Page,
  GET,
  SET,
  ActionTypes
} from './types'

export function list(): ActionTypes {
  return {
    type: LIST
  }
}

export function get(): ActionTypes {
  return {
    type: GET
  }
}

export function set(newPage: Page): ActionTypes {
  return {
    type: SET,
    payload: newPage
  }
}
