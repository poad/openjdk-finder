export const LIST = 'LIST'

export interface OpenJDK {
  id: string
  vendor: string,
  distribution: string,
  majorVersion: number,
  arch: string,
  version: string,
  installationType: string,
  extension: string | undefined,
  url: string,
  type: string,
  bundle: string,
  fx: boolean,
  os: string,
  timestamp: string | undefined,
}

export type OpenJDKState = Array<OpenJDK>

export interface State {
  items: OpenJDKState,
  page: Page

}

interface ListAction {
  type: typeof LIST
}

export const GET = 'GET'
export const SET = 'SET'

export interface Page {
  page: number,
  rowsPerPage: number,
}

export interface State {
  page: Page
}

interface GetAction {
  type: typeof GET
}

interface SetAction {
  type: typeof SET
  payload: Page
}


export type ActionTypes =
  | ListAction
  | GetAction
  | SetAction

