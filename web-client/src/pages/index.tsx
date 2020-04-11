import React from 'react'
import { Layout } from '../components/Layout';
import OpenJDKList from '../components/OpenJDKList'

class Home extends React.Component {
  render() {
    return (
      <Layout>
        <div className="hero">
          <OpenJDKList items={[]} page={{ page: 0, rowsPerPage: 10}}></OpenJDKList>
        </div>
      </Layout>
    )
  }
}

export default Home
