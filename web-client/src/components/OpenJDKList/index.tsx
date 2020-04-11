import * as React from 'react'
import { withStyles, Theme, createStyles } from '@material-ui/core/styles';
import { State, OpenJDK, Page, Filter } from '../../store/openjdk/types'
import RestClient from '../RestClient'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';
import { Link } from '@material-ui/core';
import Tooltip from '@material-ui/core/Tooltip';
import IconButton from '@material-ui/core/IconButton';
import GetAppIcon from '@material-ui/icons/GetApp';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import CircularProgress from '@material-ui/core/CircularProgress';
import Backdrop from '@material-ui/core/Backdrop';
import Dialog from '@material-ui/core/Dialog';

interface Props {
  items: Array<OpenJDK>,
  page: Page
}

const StyledTableCell = withStyles((theme: Theme) => createStyles({
  root: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  }
})
)(TableCell);

class OpenJDKList extends React.Component<Props, State> {
  props: Props
  state: State
  client: RestClient

  constructor(props: Props) {
    super(props)

    this.props = props;
    this.state = {
      items: [],
      displayItems: [],
      page: {
        page: 0,
        rowsPerPage: 10
      },
      vendors: [],
      versions: [],
      architectures: [],
      types: [],
      bundles: [],
      os: [],
      fx: undefined,
      condition: {
        vendor: undefined,
        version: undefined,
        architecture: undefined,
        type: undefined,
        bundle: undefined,
        os: undefined,
        fx: undefined
      },
      loaded: false,
      error: false,
    };
    this.client = new RestClient();
  }

  componentDidMount(): void {
    this.openLoading()
    this.fetchVendors()
    this.fetchVersions()
    this.fetchArchitectures()
    this.fetchTypes()
    this.fetchBundles()
    this.fetchOs()

    this.fetchList()
  }

  fetchList = (): void => {
    try {
      this.client.fetchList().then((items) =>
        this.filtering(items)
      )
      this.closeLoading();
    } catch (e) {
      this.openErrorDialog();
    }
  }

  openLoading = (): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      loaded: false,
      error: false
    })
  }

  closeLoading = (): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      loaded: true,
      error: false
    })
  }

  openErrorDialog = (): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      loaded: false,
      error: true
    })
  }

  fetchVendors = (): void => {
    this.client.fetchVedorList().then((vendors) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx
      })
    )
  }

  fetchVersions = (): void => {
    this.client.fetchVersionList().then((versions) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx
      })
    )
  }

  fetchArchitectures = (): void => {
    this.client.fetchArchitectureList().then((architectures) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx
      })
    )
  }

  fetchTypes = (): void => {
    this.client.fetchTypeList().then((types) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx
      })
    )
  }

  fetchBundles = (): void => {
    this.client.fetchBundleList().then((bundles) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: bundles,
        os: this.state.os,
        fx: this.state.fx
      })
    )
  }

  fetchOs = (): void => {
    this.client.fetchOsList().then((os) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: os,
        fx: this.state.fx
      })
    )
  }

  filtering = (items: Array<OpenJDK>, filter?: Filter): void => {
    var displayItems = items;
    if (filter) {
      displayItems = filter.vendor ? displayItems.filter(item => item.vendor == filter.vendor) : displayItems;
      displayItems = filter.version ? displayItems.filter(item => Number(item.version) == filter.version) : displayItems;
      displayItems = filter.architecture ? displayItems.filter(item => item.arch == filter.architecture) : displayItems;
      displayItems = filter.os ? displayItems.filter(item => item.os == filter.os) : displayItems;
      displayItems = filter.type ? displayItems.filter(item => item.type == filter.type) : displayItems;
      displayItems = filter.bundle ? displayItems.filter(item => item.bundle == filter.bundle) : displayItems;
    }

    this.setState({
      items: items,
      displayItems: displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: filter ? filter : this.state.condition,
      loaded: this.state.loaded,
      error: this.state.error,
    })
  }

  headCells = [
    { id: 'vendor', numeric: false, disablePadding: false, label: 'Vendor' },
    { id: 'version', numeric: false, disablePadding: false, label: 'Version' },
    { id: 'architecture', numeric: false, disablePadding: false, label: 'Architecture' },
    { id: 'installationType', numeric: false, disablePadding: false, label: 'Package Type' },
    { id: 'type', numeric: false, disablePadding: false, label: 'JVM implementation' },
    { id: 'bundle', numeric: false, disablePadding: false, label: 'Type' },
    { id: 'os', numeric: false, disablePadding: false, label: 'OS' },
    { id: 'fx', numeric: false, disablePadding: false, label: 'OpenFX' },
    { id: 'timestamp', numeric: false, disablePadding: false, label: 'Latest Update' },
    { id: 'download', numeric: false, disablePadding: false, label: 'Link' },
  ];

  public render(): JSX.Element {
    const setRowsPerPage = (newRowsPerPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: this.state.page.page,
          rowsPerPage: newRowsPerPage
        },
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition
      });
    };

    const setPage = (newPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: newPage,
          rowsPerPage: this.state.page.rowsPerPage
        },
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition
      });
    };

    const handleChangePage = (_: unknown, newPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: newPage,
          rowsPerPage: this.state.page.rowsPerPage
        },
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition
      });
    };

    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
      setRowsPerPage(+event.target.value);
      setPage(0);
    };

    const onChangeState = (condition: Filter) => {
      this.filtering(this.state.items, condition)
    }

    const onVendorChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: event.target.value ? event.target.value as string : undefined,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onVersionChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: event.target.value ? event.target.value as number : undefined,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      }
      );
    };

    const onArchitectureChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: event.target.value ? event.target.value as string : undefined,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      }
      );
    };

    const onTypeChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: event.target.value ? event.target.value as string : undefined,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      }
      );
    };

    const onBundleChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: event.target.value ? event.target.value as string : undefined,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      }
      );
    };

    const onOsChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: event.target.value ? event.target.value as string : undefined,
        fx: this.state.condition.fx
      }
      );
    };

    const onFxChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: event.target.value ? event.target.value as boolean : undefined,
      }
      );
    };

    return (
      <React.Fragment>
        <Backdrop open={!this.state.loaded} invisible={this.state.loaded} transitionDuration={60 * 1000}>
          <CircularProgress color="inherit" disableShrink />
        </Backdrop>
        <Dialog open={this.state.error}>error!</Dialog>
        <Container fixed>
          <FormControl fullWidth>
            <Paper variant="outlined">
              <Grid
                container
                direction="row"
                spacing={2}
              >
                <Grid item xs={true}>
                  <TextField
                    id="vendor"
                    select
                    label="Vendor"
                    value={this.state.condition.vendor}
                    onChange={onVendorChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.vendors.map(vendor =>
                      <MenuItem value={vendor}>{vendor}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="version"
                    select
                    label="Major Version"
                    value={this.state.condition.version}
                    onChange={onVersionChange}
                    size={'medium'}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.versions.map(version =>
                      <MenuItem value={version}>{version}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="architecture"
                    select
                    label="Architecture"
                    value={this.state.condition.architecture}
                    onChange={onArchitectureChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.architectures.map(architecture =>
                      <MenuItem value={architecture}>{architecture}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="type"
                    select
                    label="JVM implementation"
                    value={this.state.condition.type}
                    onChange={onTypeChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.types.map(type =>
                      <MenuItem value={type}>{type}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="bundle"
                    select
                    label="bundle type"
                    value={this.state.condition.bundle}
                    onChange={onBundleChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.bundles.map(bundle =>
                      <MenuItem value={bundle}>{bundle}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="os"
                    select
                    label="OS"
                    value={this.state.condition.os}
                    onChange={onOsChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.os.map(os =>
                      <MenuItem value={os}>{os}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="fx"
                    select
                    label="OpenFX"
                    value={this.state.condition.fx}
                    onChange={onFxChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    <MenuItem value='true'>with FX</MenuItem>
                    <MenuItem value='false'>without FX</MenuItem>
                  </TextField>
                </Grid>
              </Grid>
            </Paper>
          </FormControl>
        </Container>

        <Box component="span" m={1}>
          <Container fixed>
            <TableContainer>
              <Table size='small' stickyHeader>
                <TableHead>
                  <TableRow>
                    {this.headCells.map((headCell) => (
                      <StyledTableCell
                        key={headCell.id}
                        align={headCell.numeric ? 'right' : 'left'}
                        padding={headCell.disablePadding ? 'none' : 'default'}
                        sortDirection={false}
                      >
                        {headCell.label}
                      </StyledTableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {this.state.displayItems.slice(this.state.page.page * this.state.page.rowsPerPage, this.state.page.page * this.state.page.rowsPerPage + this.state.page.rowsPerPage).map((item) =>
                    <TableRow key={item.id}>
                      <TableCell>{item.vendor}</TableCell>
                      <TableCell>{item.version}</TableCell>
                      <TableCell>{item.arch}</TableCell>
                      <TableCell>{item.installationType}</TableCell>
                      <TableCell>{item.type}</TableCell>
                      <TableCell>{item.bundle}</TableCell>
                      <TableCell>{item.os}</TableCell>
                      <TableCell>{item.fx ? 'Y' : ''}</TableCell>
                      <TableCell>{item.timestamp}</TableCell>
                      <TableCell>
                        <Link href={item.url}>
                          <Tooltip title={item.url}>
                            <IconButton aria-label="download">
                              <GetAppIcon />
                            </IconButton>
                          </Tooltip>
                        </Link>
                      </TableCell>
                    </TableRow>
                  )}
                </TableBody>
              </Table>
            </TableContainer>
            <TablePagination
              rowsPerPageOptions={[10, 25, 100]}
              component="div"
              count={this.state.items.length}
              rowsPerPage={this.state.page.rowsPerPage}
              page={this.state.page.page}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </Container>
        </Box>
      </React.Fragment>
    );
  }
}

export default OpenJDKList;